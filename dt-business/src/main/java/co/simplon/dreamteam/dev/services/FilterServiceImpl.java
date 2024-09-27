package co.simplon.dreamteam.dev.services;

import org.springframework.stereotype.Service;

import co.simplon.dreamteam.dev.dtos.out.FilterView;
import co.simplon.dreamteam.dev.entities.Filter;
import co.simplon.dreamteam.dev.repositories.FilterRepository;

@Service
public class FilterServiceImpl implements FilterService {
	
	public final FilterRepository filterRepository;
	
	
	public FilterServiceImpl(FilterRepository filterRepository) {
		this.filterRepository = filterRepository;
	}


	@Override
	public FilterView findProjectBy() {
		return null;
	}


	@Override
	public FilterView createFilter(FilterView filterView) {
	    Filter filter = new Filter();
	    filter.setName(filterView.name());
	    filter.setKeyword(filterView.keyword());
	    filter.setFirstTopic(filterView.firstSkill());
	    filter.setSecondTopic(filterView.secondSkill());
	    
	    filterRepository.save(filter);
	    return new FilterView(filter.getName(), filter.getKeyword(), filter.getFirstTopic(), filter.getSecondTopic());
	}




}
