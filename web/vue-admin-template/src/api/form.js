import request from '@/utils/request'
const pre = '/form'

// export function list() {
//   return request({
//     url: `${pre}/list`,
//     method: 'get'
//   })
// }

export function all() {
  return request({
    url: `${pre}/all`,
    method: 'get'
  })
}

export function release(data) {
  return request({
    url: `${pre}/release`,
    method: 'post',
    data
  })
}

export function getFormInfo(data) {
  return request({
    url: `${pre}/getFormInfo/${data}`,
    method: 'get',
  })
}

export function getView(data) {
  return request({
    url: `${pre}/getView/${data}`,
    method: 'get',
  })
}
export function addView(data) {
  return request({
    url: `${pre}/addViewForm`,
    method: 'post',
    data
  })
}

export function getViewConfig(data) {
  return request({
    url: `${pre}/getViewConfig/${data}`,
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

export function create(data) {
  return request({
    url: `${pre}/create`,
    method: 'post',
    data
  })
}

