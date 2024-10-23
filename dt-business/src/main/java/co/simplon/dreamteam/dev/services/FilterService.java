package co.simplon.dreamteam.dev.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.dreamteam.dev.dtos.out.FilterView;
import co.simplon.dreamteam.dev.entities.Filter;
import co.simplon.dreamteam.dev.repositories.FilterRepository;

@Service
public class FilterService {

	private final FilterRepository filterRepository;

	public FilterService(FilterRepository filterRepository) {
		this.filterRepository = filterRepository;
	}

	@Transactional
	public FilterView createFilter(FilterView filterView) {
		// public FilterView createFilter(FilterView filterView, long developerId) {
		// long filterCount = filterRepository.countDeveloperId(developerId);
		// if (filterCount >= 5) {
		// throw new IllegalStateException("5 filtres maximum");
		// }

		Filter filter = new Filter();
		filter.setName(filterView.name());
		filter.setKeyword(filterView.keyword());
		filter.setFirstTopic(filterView.firstTopic());
		filter.setSecondTopic(filterView.secondTopic());
		// filter.setDeveloperId(filterView.developerId());

		filterRepository.save(filter);
		return new FilterView(filter.getName(), filter.getKeyword(), filter.getFirstTopic(), filter.getSecondTopic());
	}

}
