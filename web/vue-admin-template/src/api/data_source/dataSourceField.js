import request from '@/utils/request'
const pre = '/admin/data_source/field'

export function getDataSourceFieldList(tableId) {
  return request({
    url: `${pre}/list/${tableId}`,
    method: 'get'
  })
}

export function saveDataSourceField(data) {
  return request({
    url: `${pre}/save`,
    method: 'post',
    data
  })
}

