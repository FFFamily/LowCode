import request from '@/utils/request'
const pre = '/admin/application'

export function list() {
  return request({
    url: `${pre}/list`,
    method: 'get'
  })
}

export function saveApplication(data) {
  return request({
    url: `${pre}/save`,
    method: 'post',
    data
  })
}

