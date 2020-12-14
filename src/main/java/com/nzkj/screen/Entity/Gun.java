package com.nzkj.screen.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @Author: Liu yang
 * @Date: 2020/7/6 15:44
 * Describe:
 */
@Entity(name = "t_business_base_operation_gun")
public class Gun {

    @Id
    @GeneratedValue
    //id
    private long id;

    /**
     * 枪号
     */
    @Column(name = "s_number")
    private String number;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "l_pile_id")
    private Pile pile;

    @Transient
    public Long pileId;
    @Column(name = "v_serial")
    public String serial;
    @Column(name = "v_name")
    private String name;

}
