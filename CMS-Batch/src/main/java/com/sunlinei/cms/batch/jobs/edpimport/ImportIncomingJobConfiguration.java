package com.sunlinei.cms.batch.jobs.edpimport;

import java.io.File;
import java.io.FileFilter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.sunlinei.cms.batch.common.listeners.LogProcessListener;
import com.sunlinei.cms.batch.common.listeners.ProtocolListener;
import com.sunlinei.cms.batch.config.InfrastructureConfiguration;
import com.sunlinei.cms.batch.jobs.edpimport.model.VisaIncomingFile;

@Configuration
@EnableBatchProcessing
@Import({InfrastructureConfiguration.class, ImportServicesConfiguration.class})
public class ImportIncomingJobConfiguration {

	public static final String OVERRIDEN_BY_EXPRESSION_VALUE = "overriden by expression value";
	
	@Autowired
	private JobBuilderFactory jobs;
 
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	/**
	 * This bean handles the incoming file import flow for this specific job
	 * It is a sequential flow that runs different steps
	 * Step 1 - Import Visa Incoming File
	 * Step 2 - Print Visa Incoming Reconciliation Report TODO
	 * Step 3 - Import MasterCard Incoming File (In the future)
	 * Step 4 - Print MasterCard Incoming Reconciliation Report (In the future)
	 * Step 5 .....
	 * @return Job
	 */
	@Bean
	public Job importIncomingJob(){
		return jobs.get("importIncomingJob")
				.listener(protocolListener())
				.flow(impVisaIncoming())
				.end()
				.build();
	}	
	
	// tag::jobstep[] - import Visa Incoming File
	@Bean
	public Step impVisaIncoming(){
		return stepBuilderFactory.get("impVisaIncoming")
				.<VisaIncomingFile,VisaIncomingFile>chunk(10) //important to be one in this case to commit after every line read
				.reader(visaIncFileReader(OVERRIDEN_BY_EXPRESSION_VALUE))
				.processor(visaIncProcssor())
				.writer(visaIncWriter())
				.listener(logProcessListener())
				.faultTolerant()
				.skipLimit(0) //default is set to 0
//				.skip(Exception.class)
				.build();
	}	
	
	// tag::end jobstep[]
	@Bean
	@StepScope
	public FlatFileItemReader<VisaIncomingFile> visaIncFileReader(@Value("#{jobParameters['input.file.name']}") String directoryPath){
		FlatFileItemReader<VisaIncomingFile> reader = new FlatFileItemReader<VisaIncomingFile>();
//		reader.setLinesToSkip(1);//first line is title definition 
		reader.setResource(getFileFromDirectory(directoryPath));
		reader.setLineMapper(visaIncLineMapper());
		return reader; 
	}

	private Resource getFileFromDirectory(String incFile) {
		System.out.println("###FILE###"+incFile);
		File fl = new File(incFile);

//		File[] files = fl.listFiles(new FileFilter() {
//			@Override
//			public boolean accept(File file) {
//				return file.isFile();
//			}
//		});
//
//		if(files.length != 1) throw new RuntimeException("There must be only one file present in the folder to be processed");

		return new FileSystemResource(fl);
	}
	@Bean
	public LineMapper<VisaIncomingFile> visaIncLineMapper() {
		DefaultLineMapper<VisaIncomingFile> lineMapper = new DefaultLineMapper<VisaIncomingFile>();
		
		FixedLengthTokenizer  lineTokenizer = new FixedLengthTokenizer();
		
		lineTokenizer.setStrict(true);
		lineTokenizer.setNames(new String[] { "ALL" });
		lineTokenizer.setColumns(new Range[]{  new Range(1,168) });
		
		BeanWrapperFieldSetMapper<VisaIncomingFile> fieldSetMapper = new BeanWrapperFieldSetMapper<VisaIncomingFile>();
		fieldSetMapper.setTargetType(VisaIncomingFile.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(visaIncomingFileFieldSetMapper());
		
		return lineMapper;
	}

	@Bean
	public VisaIncomingFileFieldSetMapper visaIncomingFileFieldSetMapper() {
		return new VisaIncomingFileFieldSetMapper();
	}

	/** configure the processor related stuff */
    @Bean
    public ItemProcessor<VisaIncomingFile, VisaIncomingFile> visaIncProcssor() {
        return new VisaIncomingFileItemProcessor();
    }
    
    @Bean
    public ItemWriter<VisaIncomingFile> visaIncWriter() {
    	return new VisaIncWriter();
    }
    
	@Bean
	public ProtocolListener protocolListener(){
		return new ProtocolListener();
	}
 
	@Bean
	public LogProcessListener logProcessListener(){
		return new LogProcessListener();
	}    
}
