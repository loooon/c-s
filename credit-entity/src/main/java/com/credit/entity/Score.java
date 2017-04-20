package com.credit.entity;

import javax.persistence.*;

/**
 * Created by Michael Chan on 3/27/2017.
 */
@Entity
@Table(name = "tb_score")
public class Score
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "var_name")
    private String varName;

    @Column(name = "min")
    private Integer min;

    @Column(name = "max")
    private Integer max;

    @Column(name = "woe")
    private Double woe;

    @Column(name = "score")
    private Double score;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getVarName()
    {
        return varName;
    }

    public void setVarName(String varName)
    {
        this.varName = varName;
    }

    public Integer getMin()
    {
        return min;
    }

    public void setMin(Integer min)
    {
        this.min = min;
    }

    public Integer getMax()
    {
        return max;
    }

    public void setMax(Integer max)
    {
        this.max = max;
    }

    public Double getWoe()
    {
        return woe;
    }

    public void setWoe(Double woe)
    {
        this.woe = woe;
    }

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }
}
