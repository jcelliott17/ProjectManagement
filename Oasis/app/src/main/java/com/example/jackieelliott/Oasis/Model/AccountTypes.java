package com.example.jackieelliott.Oasis.Model;

/**
 * Created by Alon on 2/16/17.
 */
public class AccountTypes {
    /**
     * possible user account types allowed
     */
    public enum AccountType {
        @SuppressWarnings("EnumeratedConstantNamingConvention")User,
        @SuppressWarnings("EnumeratedConstantNamingConvention")Worker,
        @SuppressWarnings("EnumeratedConstantNamingConvention")Manager,
        @SuppressWarnings("EnumeratedConstantNamingConvention")Admin
    }
}
