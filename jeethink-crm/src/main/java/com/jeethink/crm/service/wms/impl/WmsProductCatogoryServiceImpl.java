package com.jeethink.crm.service.wms.impl;

import java.util.List;
import java.util.ArrayList;
import com.jeethink.common.core.domain.Ztree;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.WmsProductCatogoryMapper;
import com.jeethink.crm.domain.WmsProductCatogory;
import com.jeethink.crm.service.wms.IWmsProductCatogoryService;
import com.jeethink.common.core.text.Convert;

/**
 * 产品分类管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-30
 */
@Service
public class WmsProductCatogoryServiceImpl implements IWmsProductCatogoryService 
{
    @Autowired
    private WmsProductCatogoryMapper wmsProductCatogoryMapper;

    /**
     * 查询产品分类管理
     * 
     * @param productCatogoryId 产品分类管理ID
     * @return 产品分类管理
     */
    @Override
    public WmsProductCatogory selectWmsProductCatogoryById(Long productCatogoryId)
    {
        return wmsProductCatogoryMapper.selectWmsProductCatogoryById(productCatogoryId);
    }

    /**
     * 查询产品分类管理列表
     * 
     * @param wmsProductCatogory 产品分类管理
     * @return 产品分类管理
     */
    @Override
    public List<WmsProductCatogory> selectWmsProductCatogoryList(WmsProductCatogory wmsProductCatogory)
    {
        return wmsProductCatogoryMapper.selectWmsProductCatogoryList(wmsProductCatogory);
    }

    /**
     * 新增产品分类管理
     * 
     * @param wmsProductCatogory 产品分类管理
     * @return 结果
     */
    @Override
    public int insertWmsProductCatogory(WmsProductCatogory wmsProductCatogory)
    {
    	WmsProductCatogory info = wmsProductCatogoryMapper.selectWmsProductCatogoryById(wmsProductCatogory.getParentId());
    	wmsProductCatogory.setDelFlag("0");
        wmsProductCatogory.setCreateTime(DateUtils.getNowDate());        
        wmsProductCatogory.setAncestors(info.getAncestors() + "," + wmsProductCatogory.getParentId());        
        return wmsProductCatogoryMapper.insertWmsProductCatogory(wmsProductCatogory);
    }

    /**
     * 修改产品分类管理
     * 
     * @param wmsProductCatogory 产品分类管理
     * @return 结果
     */
    @Override
    public int updateWmsProductCatogory(WmsProductCatogory wmsProductCatogory)
    {
    	WmsProductCatogory newParentProductCatogory = this.selectWmsProductCatogoryById(wmsProductCatogory.getParentId());
    	WmsProductCatogory oldProductCatogory = this.selectWmsProductCatogoryById(wmsProductCatogory.getProductCatogoryId());
         if (StringUtils.isNotNull(newParentProductCatogory) && StringUtils.isNotNull(oldProductCatogory))
         {
             String newAncestors = newParentProductCatogory.getAncestors() + "," + newParentProductCatogory.getProductCatogoryId();
             String oldAncestors = oldProductCatogory.getAncestors();
             wmsProductCatogory.setAncestors(newAncestors);
             updateProductCatogoryChildren(wmsProductCatogory.getProductCatogoryId(), newAncestors, oldAncestors);
         }
        wmsProductCatogory.setUpdateTime(DateUtils.getNowDate());
        return wmsProductCatogoryMapper.updateWmsProductCatogory(wmsProductCatogory);
    }

    /**
     * 删除产品分类管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsProductCatogoryByIds(String ids)
    {
        return wmsProductCatogoryMapper.deleteWmsProductCatogoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品分类管理信息
     * 
     * @param productCatogoryId 产品分类管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsProductCatogoryById(Long productCatogoryId)
    {
        return wmsProductCatogoryMapper.deleteWmsProductCatogoryById(productCatogoryId);
    }

    /**
     * 查询产品分类管理树列表
     * 
     * @return 所有产品分类管理信息
     */
    @Override
    public List<Ztree> selectWmsProductCatogoryTree()
    {
        List<WmsProductCatogory> wmsProductCatogoryList = wmsProductCatogoryMapper.selectWmsProductCatogoryList(new WmsProductCatogory());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (WmsProductCatogory wmsProductCatogory : wmsProductCatogoryList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(wmsProductCatogory.getProductCatogoryId());
            ztree.setpId(wmsProductCatogory.getParentId());
            ztree.setName(wmsProductCatogory.getCatogoryName());
            ztree.setTitle(wmsProductCatogory.getCatogoryName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
    
    /**
     * 修改子元素关系
     * 
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateProductCatogoryChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<WmsProductCatogory> children = wmsProductCatogoryMapper.selectChildrenProductCatogoryById(deptId);
        for (WmsProductCatogory child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
        	wmsProductCatogoryMapper.updateProductCatogoryChildren(children);
        }
    }
}
