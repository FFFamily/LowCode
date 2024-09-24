import request from '@/utils/request'
const pre = '/form/rule'

// export function list() {
//   return request({
//     url: `${pre}/list`,
//     method: 'get'
//   })
// }

export function getRuleByType(formId,type) {
  return request({
    url: `${pre}/all/${type}/${formId}`,
    method: 'get'
  })
}


export function addRule(type,data) {
  return request({
    url: `${pre}/add/${type}`,
    method: 'post',
    data
  })
}

