export const mapping = {
  "now": now()
}
/**
 * 获取当前时间
 * @returns {string}
 */
function now() {
  const nowDate = new Date();
  const year = nowDate.getFullYear();
  // 月份从 0 开始，需要加 1，并且确保两位数
  const month = String(nowDate.getMonth() + 1).padStart(2, '0');
  const day = String(nowDate.getDate()).padStart(2, '0');
  const hours = String(nowDate.getHours()).padStart(2, '0');
  const minutes = String(nowDate.getMinutes()).padStart(2, '0');
  const seconds = String(nowDate.getSeconds()).padStart(2, '0');
  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
