import request from '@/utils/request'
const pre = '/form/action/condition'

export function all(formId) {
  return request({
    url: `${pre}/all/${formId}`,
    method: 'get'
  })
}
