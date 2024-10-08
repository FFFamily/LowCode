import request from '@/utils/request'
const pre = '/salary'

export function list() {
  return request({
    url: `${pre}/list`,
    method: 'post'
  })
}

