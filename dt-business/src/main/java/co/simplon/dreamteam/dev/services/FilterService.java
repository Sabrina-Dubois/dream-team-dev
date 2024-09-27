package co.simplon.dreamteam.dev.services;

import co.simplon.dreamteam.dev.dtos.out.FilterView;

public interface FilterService {
	FilterView findProjectBy();
	

	FilterView createFilter(FilterView filterView);
	

}
