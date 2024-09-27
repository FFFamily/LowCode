import request from '@/utils/request'
const pre = '/api/table'

export function config(formId, type) {
  return request({
    url: `${pre}/config/${formId}/${type}`,
    method: 'post'
  })
}

export function list(tableName) {
  return request({
    url: `${pre}/list/${tableName}`,
    method: 'post'
  })
}

export function release(data) {
  return request({
    url: `${pre}/release`,
    method: 'post',
    data
  })
}

export function add(tableName,data) {
  return request({
    url: `${pre}/add/${tableName}`,
    method: 'get',
    data
  })
}

export function find(data) {
  return request({
    url: `${pre}/find`,
    method: 'post',
    data
  })
}

export function del(tableName,fieldId) {
  return request({
    url: `${pre}/del/${tableName}/${fieldId}}`,
    method: 'get',
  })
}

export function get(tableName,fieldId) {
  return request({
    url: `${pre}/get/${tableName}/${fieldId}`,
    method: 'get',
  })
}

export function update(tableName) {
  return request({
    url: `${pre}/update/${tableName}`,
    method: 'post'
  })
}

