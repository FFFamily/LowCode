import request from '@/utils/request'
const pre = '/admin/data_source'

export function getDataSourceList() {
  return request({
    url: `${pre}/list`,
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

