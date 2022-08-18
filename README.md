### OUTPUT

```text
Task - 1 : Create a File-1, File-2
CommitId: 1
Message: Create File-1, File-2
Diff:
FileName: File-2
CREATE
+This is the file two.
FileName: File-1
CREATE
+This is the file one.

File directory content - After 1st Commit
File-2
This is the file two.
File-1
This is the file one.


Task - 2 : Create File-3, append to File-2 and trim last 4 characters of File-1
CommitId: 2
Message: Create File-3, append to File-2 and trim last 4 characters of File-1
Diff:
FileName: File-2
APPEND
+This is the appended text
FileName: File-1
TRIM
-one.
FileName: File-3
CREATE
+This is the file three.

File directory content - After 2nd Commit
File-2
This is the file two.This is the appended text
File-1
This is the file
File-3
This is the file three.


Task - 3 : Delete File-1 and append to File-3
CommitId: 3
Message: Delete File-1 and append to File-3
Diff:
FileName: File-1
DELETE
-This is the file
FileName: File-3
APPEND
+Append text

File directory content - After 3rd Commit
File-2
This is the file two.This is the appended text
File-3
This is the file three.Append text

-----------Status-----------
CommitId: 3
Message: Delete File-1 and append to File-3
Diff:
FileName: File-1
DELETE
-This is the file
FileName: File-3
APPEND
+Append text

-----------Checkout-----------

Contents of the File System on commit - 2
File-2
This is the file two.This is the appended text
File-1
This is the file
File-3
This is the file three.

Contents of the File System on commit - 3
File-2
This is the file two.This is the appended text
File-3
This is the file three.Append text


Task - 4 : Create new files only and usage of log
CommitId: 4
Message: Create new files only and usage of log
Diff:
FileName: File-5
CREATE
+This is file five
FileName: File-4
CREATE
+This is file four

File directory content - After 4rd Commit
File-5
This is file five
File-2
This is the file two.This is the appended text
File-4
This is file four
File-3
This is the file three.Append text

-----------Log-----------

CommitId: 4
Message: Create new files only and usage of log
Diff:
FileName: File-5
CREATE
+This is file five
FileName: File-4
CREATE
+This is file four

CommitId: 3
Message: Delete File-1 and append to File-3
Diff:
FileName: File-1
DELETE
-This is the file
FileName: File-3
APPEND
+Append text

CommitId: 2
Message: Create File-3, append to File-2 and trim last 4 characters of File-1
Diff:
FileName: File-2
APPEND
+This is the appended text
FileName: File-1
TRIM
-one.
FileName: File-3
CREATE
+This is the file three.

CommitId: 1
Message: Create File-1, File-2
Diff:
FileName: File-2
CREATE
+This is the file two.
FileName: File-1
CREATE
+This is the file one.

-----------Checkout-----------

Commit message: Create File-1, File-2
Contents of the File System on commit - 1
File-2
This is the file two.
File-1
This is the file one.

Commit message: Create File-3, append to File-2 and trim last 4 characters of File-1
Contents of the File System on commit - 2
File-2
This is the file two.This is the appended text
File-1
This is the file
File-3
This is the file three.

Commit message: Delete File-1 and append to File-3
Contents of the File System on commit - 3
File-2
This is the file two.This is the appended text
File-3
This is the file three.Append text

Commit message: Create new files only and usage of log
Contents of the File System on commit - 4
File-5
This is file five
File-2
This is the file two.This is the appended text
File-4
This is file four
File-3
This is the file three.Append text
```