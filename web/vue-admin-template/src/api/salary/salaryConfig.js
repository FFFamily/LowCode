import request from '@/utils/request'
const pre = '/salary/config'


export function addConfig(data) {
  return request({
    url: `${pre}/add`,
    method: 'post',
    data: data
  })
}

export function getConfig() {
  return request({
    url: `${pre}/get`,
    method: 'get',
  })
}
