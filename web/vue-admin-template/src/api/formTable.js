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
