package com.rcszh.lowcode.common.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Treeselect 树结构实体类
 */
@Getter
@Setter
public class TreeSelect implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

//    public TreeSelect()
//    {
//
//    }
//
//    public TreeSelect(SysDept dept)
//    {
//        this.id = dept.getDeptId();
//        this.label = dept.getDeptName();
//        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
//    }
//
//    public TreeSelect(SysMenu menu)
//    {
//        this.id = menu.getMenuId();
//        this.label = menu.getMenuName();
//        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
//    }

    public static  <T> TreeSelect buildTree(T data, Function<T,Long> getIdFunc,Function<T,String> getLabelFunc,Function<T,List<T>> getChildFunc){
        TreeSelect treeSelect = new TreeSelect();
        treeSelect.setId(getIdFunc.apply(data));
        treeSelect.setLabel(getLabelFunc.apply(data));
        treeSelect.setChildren(getChildFunc.apply(data).stream().map(item -> buildTree(item,getIdFunc,getLabelFunc,getChildFunc)).collect(Collectors.toList()));
        return  treeSelect;
    }
}
