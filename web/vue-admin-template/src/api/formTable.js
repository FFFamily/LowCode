import request from '@/utils/request'
const pre = '/form/table'

// export function list() {
//   return request({
//     url: `${pre}/list`,
//     method: 'get'
//   })
// }

export function getAllFormTable() {
  return request({
    url: `${pre}/all`,
    method: 'get'
  })
}

export function getTableById(data) {
  return request({
    url: `${pre}/get/${data}`,
    method: 'get'
  })
}

export function createChildTable(data) {
  return request({
    url: `${pre}/createChildTable`,
    method: 'post',
    data
  })
}
