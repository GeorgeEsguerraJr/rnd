package com.sunlinei.cms.batch.jobs.edpimport;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunlinei.cms.batch.jobs.edpimport.dao.ImportDao;
import com.sunlinei.cms.batch.jobs.edpimport.model.VisaIncomingFile;

public class VisaIncomingFileItemProcessor implements ItemProcessor<VisaIncomingFile, VisaIncomingFile> {

	private static final int TIMEOUT = 10;

	@Autowired
	ImportDao readDao;

	@Override
	public VisaIncomingFile process(VisaIncomingFile item) throws Exception {
		
//		if(isPodcastAlreadyInTheDirectory(item.getPodcast().getUrl())) {
//			return null;
//		}
//		
//		String[] categories = item.getCategories().trim().split("\\s*,\\s*");		
//
//		item.getPodcast().setAvailability(org.apache.http.HttpStatus.SC_OK);
//		
//		//set etag and last modified attributes for the podcast
//		setHeaderFieldAttributes(item.getPodcast());
//		
//		//set the other attributes of the podcast from the feed 
//		podcastAndEpisodeAttributesService.setPodcastFeedAttributes(item.getPodcast());
//				
//		//set the categories
//		List<Category> categoriesByNames = readDao.findCategoriesByNames(categories);
//		item.getPodcast().setCategories(categoriesByNames);
//		
//		//set the tags
//		setTagsForPodcast(item);
//		
//		//build the episodes 
//		setEpisodesForPodcast(item.getPodcast());
		
		return item;
	}

	
	
}
