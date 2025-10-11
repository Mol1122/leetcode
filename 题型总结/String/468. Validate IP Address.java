/* Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.

A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.

A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:

1 <= xi.length <= 4
xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
Leading zeros are allowed in xi.
For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.

 

Example 1:

Input: queryIP = "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:

Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"
Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:

Input: queryIP = "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address. */

class Solution {
    public String validIPAddress(String ip) {
        if (ip == null || ip.length() == 0) {
            return "Neither";
        }
        int count1 = 0, count2 = 0;
        for (char c : ip.toCharArray()) {
            if (c == '.') {
                count1++;
            } else if (c == ':') {
                count2++;
            }
        }
        if (count1 == 3) {
            return validateIP4(ip);
        } else if (count2 == 7) {
            return validateIP6(ip);
        }
        return "Neither";
    }

    private String validateIP4(String ip) {
        String[] strs = ip.split("\\.");
        
        if (strs.length != 4) {
            return "Neither";
        }

        for (String str : strs) {
            if (str.length() == 0 || str.length() > 3) {
                return "Neither";
            }
            if (str.length() > 1 && str.startsWith("0")) {
                return "Neither";
            }
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return "Neither";
                }
            }
            int num = Integer.parseInt(str);
            if (num > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    private String validateIP6(String ip) {
        String[] strs = ip.split(":");
        String hex = "0123456789abcdefABCDEF";

        if (strs.length != 8) {
            return "Neither";
        }

        for (String str : strs) {
            if (str.length() < 1 || str.length() > 4) {
                return "Neither";
            }
            for (char c : str.toCharArray()) {
                 if (hex.indexOf(c) == -1) {
                    return "Neither";
                 }
            }
        }
        return "IPv6";
    }
}