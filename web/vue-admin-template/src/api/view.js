import request from '@/utils/request'
const pre = '/form/view'


export function viewList(formId) {
  return request({
    url: `${pre}/list/${formId}`,
    method: 'get'
  })
}

export function getViewConfig(viewFormId) {
  return request({
    url: `${pre}/getViewConfig/${viewFormId}`,
    method: 'get',
  })
}

export function updateViewConfig(data) {
  return request({
    url: `${pre}/updateViewConfig`,
    method: 'post',
    data
  })
}
export function addViewForm(data) {
  return request({
    url: `${pre}/addViewForm`,
    method: 'post',
    data
  })
}


