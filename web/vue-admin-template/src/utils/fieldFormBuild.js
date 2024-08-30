// 生成表字段信息
const type_enum = ["text"]

let textFieldInfo = {
  html:[
    {
      type: "input",
      label: "字段名称",
      prop: "name",
      propValue:""
    },
    {
      type: "input",
      label: "字段标识",
      prop: "fieldName",
      propValue:""
    }
  ],
  formData:{
    type: "text",
    fieldType: "String"
  }
}

export function generateTableFieldInfo(fieldType) {
  if (!checkType(fieldType)){
    return []
  }
  if (fieldType === "text") {
    return textFieldInfo;
  }
}


export function checkType(fieldType) {
  return type_enum.includes(fieldType);
}
