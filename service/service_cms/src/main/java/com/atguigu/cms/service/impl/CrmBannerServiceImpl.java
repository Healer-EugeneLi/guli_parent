package com.atguigu.cms.service.impl;

import com.atguigu.cms.entity.CrmBanner;
import com.atguigu.cms.mapper.CrmBannerMapper;
import com.atguigu.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 获取所有的banner
     *
     * @return
     */
    @Override
    @Cacheable(key = "'getBannerAll'",value = "banner")
    public List<CrmBanner> getBannerAll() {

        //按照id进行降序 只显示前两条
        QueryWrapper<CrmBanner> wrapper=new QueryWrapper<>();
        wrapper.orderByAsc("id");
        wrapper.last("limit 2");//sql 语句拼接
        List<CrmBanner> list = baseMapper.selectList(wrapper);

        return list;
    }

    /**
     * 通过id获取banner
     *
     * @param id
     * @return
     */
    @Override
    public CrmBanner getBannerById(String id) {
        CrmBanner crmBanner = baseMapper.selectById(id);
        return crmBanner;
    }

    /**
     * 新增banner
     *
     * @param banner
     */
    @Override
    @CacheEvict(value = "banner",allEntries = true)//allEntries表示方法调用后将清空缓存存放在value命名空间的数据
    public void saveBanner(CrmBanner banner) {

        baseMapper.insert(banner);
    }

    /**
     * 更新banner
     *
     * @param banner
     */
    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public void updateBannerById(CrmBanner banner) {

        baseMapper.updateById(banner);
    }

    /**
     * 移除banner
     *
     * @param id
     */
    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }
}
