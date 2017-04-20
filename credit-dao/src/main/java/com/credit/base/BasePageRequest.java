package com.credit.base;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class BasePageRequest extends PageRequest
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private long total = 0;

    public BasePageRequest(int page, int size, long total)
    {
        super(page, size);
        this.total = total;
    }
    
    public BasePageRequest(int page, int size, long total, Sort sort)
    {
        super(page, size, sort);
        this.total = total;
    }

    public BasePageRequest(int page, int size, long total, Direction direction, String[] properties)
    {
        super(page, size, direction, properties);
        this.total = total;
    }
    
    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }
    
}
