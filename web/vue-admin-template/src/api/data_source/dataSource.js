import request from '@/utils/request'
const pre = '/admin/data_source'

export function list() {
  return request({
    url: `${pre}/list`,
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: `${pre}/save`,
    method: 'post',
    data
  })
}

