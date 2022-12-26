בחרנו להשתמש במימוש של המחלקה UndoableStringBuilder שפורסמה במודל כפתרון למטלה 0. 

נממש את תבנית העיצוב Observer.
קיימות 2 מחלקות שהיה עלינו לממש : 
1) ConcreteMember- מחלקה שמייצגת Member מסוים. המחלקה תחזיק string name ו-UndoableStringBuilder usb.
מייצג את הObservers- תפקידם להתעדכן בשינויים.
2)GroupAdmin- מחלקה שתחזיק מבני נתונים מסוג List של ConcreteMember כדי לעדכן במידה וצריך את כל חברי
הרשימה לאחר שנעשה שינוי כלשהו בstring שהם מחזיקים מסוג UndoableStringBuilder. 
מייצג את הObservable- תפקידו לעדכן על שינויים.

כדי להוסיף/להסיר Member לרשימה שמוחזקת בGroupAdmin ניעזר במתודות register ו-unregister.
בעת הוספת Member, הוא יקבל את הUndoableStringBuilder האחרון שעודכן ומעתה ואילך הוא גם יהיה פתוח
לעדכונים נוספים.

כדי שאכן ה-UndoableStringBuilder יתעדכן אצל כל אחד מה-Members, נשתמש במתודת Update שתפקידה לעדכן את 
כלל ה-members על כל פעולה (append, delete, undo, insert) שמתבצעת על ה-UndoableStringBuilder שקיים אצל כל
Member. 

כמובן שהוספנו מחלקת TEST שתבדוק שאכן השינויים מתעדכנים אצל כלל ה-Members.

