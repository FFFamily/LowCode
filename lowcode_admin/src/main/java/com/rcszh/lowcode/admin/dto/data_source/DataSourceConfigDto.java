package com.rcszh.lowcode.admin.dto.data_source;

import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Setter
public class DataSourceConfigDto{
    private CoreDataSourceConfig config;
    private List<String> dataSourceIdList;
}
