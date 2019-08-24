/* Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Please implement encode and decode

Example
Example1

Input: ["lint","code","love","you"]
Output: ["lint","code","love","you"]
Explanation:
One possible encode method is: "lint:;code:;love:;you"
Example2

Input: ["we", "say", ":", "yes"]
Output: ["we", "say", ":", "yes"]
Explanation:
One possible encode method is: "we:;say:;:::;yes" */

public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            for (char c : str.toCharArray()) {
                if (c == ':') {
                    sb.append("::");
                } else {
                    sb.append(c);
                }
            }
            sb.append(":;");
        }
        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        int i = 0;
        char[] sc = str.toCharArray();
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        
        while (i < str.length()) {
            if (sc[i] == ':') {
                if (sc[i + 1] == ';') {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                    i += 2;
                } else {
                    sb.append(sc[i + 1]);
                    i += 2;
                }
            } else {
                sb.append(sc[i]);
                i++;
            }
        }
        return result;
    }

//time: O(n), space: O(n)