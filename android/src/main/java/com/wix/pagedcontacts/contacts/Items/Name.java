package com.wix.pagedcontacts.contacts.Items;

import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;

import com.facebook.react.bridge.WritableMap;
import com.wix.pagedcontacts.contacts.Field;

public class Name extends ContactItem {
    private String namePrefix;
    private String givenName;
    private String middleName;
    private String familyName;
    private String nameSuffix;
    private String phoneticGivenName;
    private String phoneticMiddleName;
    private String phoneticFamilyName;

    Name() {

    }

    public Name(Cursor cursor) {
        super(cursor);
        fillFromCursor();
    }

    private void fillFromCursor() {
        namePrefix = getString(StructuredName.PREFIX);
        givenName = getString(StructuredName.GIVEN_NAME);
        middleName = getString(StructuredName.MIDDLE_NAME);
        familyName = getString(StructuredName.FAMILY_NAME);
        nameSuffix = getString(StructuredName.SUFFIX);
        phoneticGivenName = getString(StructuredName.PHONETIC_GIVEN_NAME);
        phoneticMiddleName = getString(StructuredName.PHONETIC_MIDDLE_NAME);
        phoneticFamilyName = getString(StructuredName.PHONETIC_FAMILY_NAME);
    }

    @Override
    public void fillMap(WritableMap map) {
        putString(map, Field.givenName, givenName);
        putString(map, Field.namePrefix, namePrefix);
        putString(map, Field.middleName, middleName);
        putString(map, Field.familyName, familyName);
        putString(map, Field.nameSuffix, nameSuffix);
        putString(map, Field.phoneticGivenName, phoneticGivenName);
        putString(map, Field.phoneticMiddleName, phoneticMiddleName);
        putString(map, Field.phoneticFamilyName, phoneticFamilyName);
    }
}
