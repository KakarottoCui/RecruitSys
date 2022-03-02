package com.codeyuaiiao.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeyuaiiao.entity.TbAdmin;
import com.codeyuaiiao.mapper.TbAdminMapper;
import com.codeyuaiiao.service.TbAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author codeyuaiiao
 * @since 2021-01-29
 */
@Service
public class TbAdminServiceImpl extends ServiceImpl<TbAdminMapper, TbAdmin> implements TbAdminService {

}
