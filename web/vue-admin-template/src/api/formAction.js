import request from '@/utils/request'
const pre = '/form/action'

export function add(data) {
  return request({
    url: `${pre}/add`,
    method: 'post',
    data
  })
}
