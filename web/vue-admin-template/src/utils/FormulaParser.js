import {mapping} from '@/utils/FormulaMethod'
const regex = /\$func+\(([^)]+)\)/;

export function parser(formula) {
  const match = formula.match(regex);
  if (match) {
    return mapping[match[1]]; // 输出: now
  } else {
    return formula;
  }
}

/**
 * 方法解析
 */
export function MethodParser(formula,args) {
  const regex = /#(\w+)?\(\$([^$]+)\$?\)?#/
  const match = formula.match(regex);
  if (match) {
    let functionName = match[1];
    let variable = match[2];
    let result = mapping[functionName](args.map(_ => _[variable]))
    console.log(result)
    return  result;
  } else {
    console.log("No match found.");
  }
}

/**
 * 方法解析
 */
export function ArgParser(formula) {
  const regex = /\$(\w+)\$/
  const match = formula.match(regex);
  if (match) {
    return match[1]
  } else {
    console.log("No match found.");
  }
}

/**
 * 方法解析
 */
export function hasMethod(formula) {
  const regex = /#(\w+)?\(\$([^$]+)\$?\)?#/
  const match = formula.match(regex);
  if (match) {
    let functionName = match[1] || undefined;
    return functionName !== undefined
  } else {
    console.log("No match found.");
    return false;
  }
}
