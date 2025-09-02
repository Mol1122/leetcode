/* You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.

 

Example 1:

Input: path = "/home/"

Output: "/home"

Explanation:

The trailing slash should be removed.

Example 2:

Input: path = "/home//foo/"

Output: "/home/foo"

Explanation:

Multiple consecutive slashes are replaced by a single one.

Example 3:

Input: path = "/home/user/Documents/../Pictures"

Output: "/home/user/Pictures"

Explanation:

A double period ".." refers to the directory up a level (the parent directory).

Example 4:

Input: path = "/../"

Output: "/"

Explanation:

Going one level up from the root directory is not possible.

Example 5:

Input: path = "/.../a/../b/c/../d/./"

Output: "/.../b/d"

Explanation:

"..." is a valid name for a directory in this problem. */
]

//Method 1
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        String result = "/";
        String[] strs = path.split("/+");
        List<String> list = new ArrayList<>();
        
        for (String str : strs) {
            if (str.equals("..")) {
                if (list.size() > 0) {
                    list.remove(list.size() - 1);
                }
            } else if (!str.equals(".") && str.length() > 0) {
                list.add(str);
            }
        }
        for (String s : list) {
            result += s + "/";
        }
        if (result.length() > 1) {
            return result.substring(0, result.length() - 1);
        }
        return result;
    }
}

//Method 2
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "";
        }
        String result = "/";
        String[] strs = path.split("/+");
        Deque<String> deque = new ArrayDeque<>();

        for (String str : strs) {
            if (str.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (str.equals(".")) {
                continue;
            } else if (str.length() > 0) {
                deque.offerLast(str);
            }
        }
        while (!deque.isEmpty()) {
            result += deque.pollFirst() + "/";
        }
        if (result.length() > 1) {
            return result.substring(0, result.length() - 1);
        }
        return result;
    }
}
//time: O(n), space: O(n)