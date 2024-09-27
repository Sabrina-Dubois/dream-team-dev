package co.simplon.dreamteam.developers.services;

import co.simplon.dreamteam.developers.dtos.out.FilterView;

public interface FilterService {
	FilterView findProjectBy();
	

	FilterView createFilter(FilterView filterView);
	

}
