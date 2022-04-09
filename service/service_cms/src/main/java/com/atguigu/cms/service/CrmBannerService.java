package com.atguigu.cms.service;

import com.atguigu.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-05
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 获取所有的banner
     * @return
     */
    List<CrmBanner> getBannerAll();


    /**
     * 通过id获取banner
     * @param id
     * @return
     */
    CrmBanner getBannerById(String id);

    /**
     * 新增banner
     * @param banner
     */
    void saveBanner(CrmBanner banner);

    /**
     * 更新banner
     * @param banner
     */
    void updateBannerById(CrmBanner banner);

    /**
     * 移除banner
     * @param id
     */
    void removeBannerById(String id);
}
