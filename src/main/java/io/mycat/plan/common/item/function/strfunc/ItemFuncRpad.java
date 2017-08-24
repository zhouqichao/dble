package io.mycat.plan.common.item.function.strfunc;

import io.mycat.plan.common.item.Item;
import io.mycat.plan.common.item.function.ItemFunc;

import java.util.List;


public class ItemFuncRpad extends ItemStrFunc {

    public ItemFuncRpad(List<Item> args) {
        super(args);
    }

    @Override
    public final String funcName() {
        return "rpad";
    }

    @Override
    public String valStr() {
        String str = args.get(0).valStr();
        int count = args.get(1).valInt().intValue();
        String pad = args.get(2).valStr();
        if (args.get(0).isNull() || args.get(1).isNull() || count < 0 || args.get(2).isNull()) {
            this.nullValue = true;
            return EMPTY;
        }
        if (count < str.length()) {
            return str.substring(0, count);
        }
        int padLen = pad.length();
        if (padLen <= 0) {
            this.nullValue = true;
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(str);
        count -= str.length();
        while (count >= padLen) {
            sb.append(pad);
            count -= padLen;
        }
        if (count > 0) {
            sb.append(pad.substring(0, count));
        }
        return sb.toString();
    }

    @Override
    public ItemFunc nativeConstruct(List<Item> realArgs) {
        return new ItemFuncRpad(realArgs);
    }
}
