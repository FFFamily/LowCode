import request from '@/utils/request'
const pre = '/admin/data_source/table'

export function getDataSourceTableList(sourceId) {
  return request({
    url: `${pre}/list/${sourceId}`,
    method: 'get'
  })
}

export function saveDataSourceTable(data) {
  return request({
    url: `${pre}/save`,
    method: 'post',
    data
  })
}

