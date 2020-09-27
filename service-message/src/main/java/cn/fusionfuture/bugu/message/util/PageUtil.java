package cn.fusionfuture.bugu.message.util;

import com.github.pagehelper.PageInfo;

public class PageUtil<T> extends PageInfo<T>{
    public void copyAtrr(PageInfo<T> source,PageInfo<T> des){
//        "pageNum": 1,
//                "pageSize": 9,
//                "size": 9,
//                "startRow": 0,
//                "endRow": 8,
//                "pages": 1,
//                "prePage": 0,
//                "nextPage": 0,
//                "isFirstPage": true,
//                "isLastPage": true,
//                "hasPreviousPage": false,
//                "hasNextPage": false,
//                "navigatePages": 5,
//                "navigatepageNums": [
//        1
//    ],
//        "navigateFirstPage": 1,
//                "navigateLastPage": 1
        des.setTotal(source.getTotal());
        des.setPageNum(source.getPageNum());
        des.setPageSize(source.getPageSize());
        des.setSize(source.getSize());
        des.setStartRow(source.getStartRow());
        des.setEndRow(source.getEndRow());
        des.setPages(source.getPages());
        des.setNextPage(source.getNextPage());
        des.setPrePage(source.getPrePage());
        des.setIsFirstPage(source.isIsFirstPage());
        des.setIsLastPage(source.isIsLastPage());
        des.setHasNextPage(source.isHasNextPage());
        des.setHasPreviousPage(source.isHasPreviousPage());
        des.setNavigatePages(source.getNavigatePages());
        des.setNavigatepageNums(source.getNavigatepageNums());
    }
}
