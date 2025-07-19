package movie.system.dto;

import java.util.List;

//public class PaginatedResponse<T> {
//    private List<T> data;
//    private long totalRecords;
//
//    public PaginatedResponse(List<T> data, long totalRecords) {
//        this.data = data;
//        this.totalRecords = totalRecords;
//    }
//
//    // Include getters if using Jackson to convert to JSON
//    public List<T> getData() {
//        return data;
//    }
//
//    public long getTotalRecords() {
//        return totalRecords;
//    }
//}


public class PaginatedResponse<T> {

    private List<T> data;
    private long totalRecords;
    private int pageNumber;
    private int pageSize;

    public PaginatedResponse(List<T> data, long totalRecords, int pageNumber, int pageSize) {
        this.data = data;
        this.totalRecords = totalRecords;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    // Getters and Setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}


