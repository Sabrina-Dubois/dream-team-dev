package co.simplon.dtbusiness.services;

import co.simplon.dtbusiness.dtos.out.FilterView;

public interface FilterService {
	FilterView findProjectBy();
	

	FilterView createFilter(FilterView filterView);
	

}
