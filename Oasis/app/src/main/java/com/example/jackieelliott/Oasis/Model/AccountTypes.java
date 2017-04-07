package com.example.jackieelliott.Oasis.Model;

/**
 * Class AccountTypes (holds an enum left over from old implementation)
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
