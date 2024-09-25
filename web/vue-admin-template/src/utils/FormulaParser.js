import {mapping} from '@/utils/FormulaMethod'
const regex = /\$func+\(([^)]+)\)/;

export function parser(formula) {
  const match = formula.match(regex);
  if (match) {
    return mapping[match[1]]; // 输出: now
  } else {
    return null;
  }
}
