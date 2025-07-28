package leet.code.solutions.stack;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/*
https://leetcode.com/problems/simplify-path/

Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//')
are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.

The canonical path should have the following format:

The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.

Example 1:
Input: path = "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
Input: path = "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
Input: path = "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Constraints:

1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.
 */
public class SimplifyPath {
    public static void main(String[] args) {
       String path = "/home/kek/../foo/";
       String converted = simplifyPath(path);
        System.out.println(converted);
    }

    private static String simplifyPath(String path) {
        String[] DIRS = path.split("/");
        Stack<String> stack = new Stack<>();

        for(String dir : DIRS) {
            if(dir.isEmpty() || dir.equals(".")){//ignore dot .
                continue;
            }

            if(dir.equals("..")){//two dots scrapes what is before it
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(dir);
            }
        }

        return "/"  + String.join("/", stack);
    }

    public static String shortestEquivalentPath(String path) {
        if (path.equals("")) {
            throw new IllegalArgumentException("Empty string is not a legal path.");
        }

        Deque<String> pathNames = new LinkedList<>();

        if (path.startsWith("/")) { // Special case: starts with which is an absolute path.
            pathNames.addFirst("/");
        }

        for (String token : path.split("/")) {
            if (token.equals("..")) {
                if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
                    pathNames.addFirst(token);
                } else {
                    if (pathNames.peekFirst().equals("/")) {
                        throw new IllegalArgumentException("Path error, trying to go up root " + path);
                        //pathNames.addFirst(token); //- leetcode
                    }
                    pathNames.removeFirst();
                }
            } else if (!token.equals(".") && !token.isEmpty()) {// Must be a name.

                pathNames.addFirst(token);
            }
        }

        StringBuilder result = new StringBuilder();

        if (!pathNames.isEmpty()) {
            Iterator<String> it = pathNames.descendingIterator();
            String prev = it.next();
            result.append(prev);
            while (it.hasNext()) {
                if (!prev.equals("/")) {
                    result.append("/");
                }
                prev = it.next();
                result.append(prev);
            }
        }
        return result.toString();
    }
}
