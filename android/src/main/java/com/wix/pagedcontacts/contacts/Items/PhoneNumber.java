package com.wix.pagedcontacts.contacts.Items;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.facebook.react.bridge.WritableMap;
import com.wix.pagedcontacts.contacts.query.QueryParams;

class PhoneNumber extends ContactItem {
    public String type;
    private String number;

    PhoneNumber(Cursor cursor) {
        super(cursor);
        fillFromCursor();
    }

    private void fillFromCursor() {
        String number = getString(ContactsContract.CommonDataKinds.Phone.NUMBER);
        String normalizedNumber = getString(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER);
        this.number = number != null ? number : normalizedNumber;

        Integer type = getInt(ContactsContract.CommonDataKinds.Phone.TYPE);
        String label = getString(ContactsContract.CommonDataKinds.Phone.LABEL);
        this.type = getType(type, label);
    }

    private String getType(Integer type, String label) {
        if (type == null) {
            throw new InvalidCursorTypeException();
        }
        switch (type) {
            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                return "home";
            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                return "work";
            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                return "mobile";
            case ContactsContract.CommonDataKinds.Phone.TYPE_PAGER:
                return "pager";
            case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
                return "fax_work";
            case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
                return "fax_home";
            case ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM:
                return label;
            default:
                return "other";
        }
    }

    @Override
    protected void fillMap(WritableMap map, QueryParams params) {
        addToMap(map, "label", type);
        addToMap(map, "value", number);
    }
}
