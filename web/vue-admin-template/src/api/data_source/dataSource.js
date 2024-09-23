import request from '@/utils/request'
const pre = '/data_source'

export function getDataSourceList() {
  return request({
    url: `${pre}/list`,
    method: 'get'
  })
}

export function getDataSourceConfigList() {
  return request({
    url: `${pre}/config/list`,
    method: 'get'
  })
}

export function saveDataSource(data) {
  return request({
    url: `${pre}/save`,
    method: 'post',
    data
  })
}
export function saveDataSourceConfig(data) {
  return request({
    url: `${pre}/config/save`,
    method: 'post',
    data
  })
}

