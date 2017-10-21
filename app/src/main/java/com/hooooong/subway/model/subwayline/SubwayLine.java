package com.hooooong.subway.model.subwayline;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class SubwayLine {
    private SearchSTNBySubwayLineService SearchSTNBySubwayLineService;

    public SearchSTNBySubwayLineService getSearchSTNBySubwayLineService() {
        return SearchSTNBySubwayLineService;
    }

    public void setSearchSTNBySubwayLineService(SearchSTNBySubwayLineService SearchSTNBySubwayLineService) {
        this.SearchSTNBySubwayLineService = SearchSTNBySubwayLineService;
    }

    @Override
    public String toString() {
        return "ClassPojo [SearchSTNBySubwayLineService = " + SearchSTNBySubwayLineService + "]";
    }
}
