// 生成表字段信息
const type_enum = ["input", "number","radio"]
let baseInfo = [
  {
    type: "number",
    label: "顺序",
    prop: "index",
    propValue:0
  },
  {
    type: "switch",
    label: "是否只读",
    prop: "readOnly",
    propValue:false
  },
  {
    type: "switch",
    label: "是否必填",
    prop: "required",
    propValue:false
  }
]

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
    },
    {
      type: "input",
      label: "正则校验规则",
      prop: "pattern",
      propValue:""
    },
    {
      type: "number",
      label: "校验最大长度",
      prop: "maxLength",
      propValue:undefined
    },
    {
      type: "number",
      label: "校验最小长度",
      prop: "minLength",
      propValue: undefined
    },
    {
      type: "input",
      label: "默认值",
      prop: "defaultValue",
      propValue:""
    }
  ].concat(baseInfo),
  formData:{
    type: "input",
    fieldType: "String"
  }
}


let numberFieldInfo = {
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
    },
    {
      type: "input",
      label: "默认值",
      prop: "defaultValue",
      propValue:""
    },
    {
      type: "number",
      label: "校验最大值(大于)",
      prop: "maximum",
      propValue:undefined
    },
    {
      type: "number",
      label: "校验最大值（大于等于)",
      prop: "exclusiveMaximum",
      propValue: undefined
    },
    {
      type: "number",
      label: "校验最小值(小于)",
      prop: "minimum",
      propValue: undefined
    },
    {
      type: "number",
      label: "最小值（小于等于）",
      prop: "exclusiveMinimum",
      propValue: undefined
    }
  ].concat(baseInfo),
  formData:{
    type: "number",
    fieldType: "Number"
  }
}

let radioFieldInfo = {
  html:[
    {
      type: "input.list",
      label: "枚举值（用|隔开）",
      prop: "enumValues",
      propValue:[]
    }
  ],
  formData:{
    type: "radio",
    fieldType: "Radio"
  }

}


export function generateTableFieldInfo(fieldType) {
  if (!checkType(fieldType)){
    return []
  }
  console.log("接收到的字段为："+fieldType)
  if (fieldType === "input") {
    return textFieldInfo;
  }else if (fieldType === "number") {
    return numberFieldInfo;
  }else if (fieldType === "radio") {
    return radioFieldInfo;
  }
}


export function checkType(fieldType) {
  return type_enum.includes(fieldType);
}
