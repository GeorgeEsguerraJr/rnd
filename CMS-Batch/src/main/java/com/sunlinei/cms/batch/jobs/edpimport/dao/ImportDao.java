package com.sunlinei.cms.batch.jobs.edpimport.dao;

import com.sunlinei.cms.batch.db.entities.VisaInc;

public interface ImportDao {

//	public List<Category> findCategoriesByNames(String[] categoryNames);
//
//	public List<Tag> getTagsByNames(String[] tags);
//
//	public Podcast getPodcastByFeedUrl(String url);		
//	
//	public User getMeUser();
	
	public void addVisaIncRec(VisaInc rec);

}
