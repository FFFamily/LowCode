import request from '@/utils/request'
const pre = '/salary/config/group'


export function addGroup(data) {
  return request({
    url: `${pre}/add`,
    method: 'post',
    data: data
  })
}

