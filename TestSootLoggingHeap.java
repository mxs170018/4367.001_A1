import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import soot.*;
import soot.jimple.AssignStmt;
import soot.jimple.DefinitionStmt;
import soot.jimple.FieldRef;
import soot.jimple.IdentityStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.IntConstant;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.Jimple;
import soot.jimple.NullConstant;
import soot.jimple.StaticFieldRef;
import soot.jimple.Stmt;
import soot.jimple.StringConstant;
import soot.options.Options;
//package default; 
public class TestSootLoggingHeap extends BodyTransformer {

	private static SootMethodRef logFieldAccMethod;
	

	public static void main(String[] args)	{

		String mainclass = "HelloThread";

		//output Jimple
		//Options.v().set_output_format(1);

//		//set classpath
	    String javapath = System.getProperty("java.class.path");
	    String jredir = System.getProperty("java.home")+"/lib/rt.jar";
	    String path = javapath+File.pathSeparator+jredir;
	    Scene.v().setSootClassPath(path);

        //add an intra-procedural analysis phase to Soot
	    TestSootLoggingHeap analysis = new TestSootLoggingHeap();
	    PackManager.v().getPack("jtp").add(new Transform("jtp.TestSootLoggingHeap", analysis));

        //load and set main class
	    Options.v().set_app(true);
	    SootClass appclass = Scene.v().loadClassAndSupport(mainclass);
	    Scene.v().setMainClass(appclass);
		SootClass logClass = Scene.v().loadClassAndSupport("Log");
		logFieldAccMethod = logClass.getMethod("void logFieldAcc(java.lang.Object,java.lang.String,boolean,boolean)").makeRef();
	    Scene.v().loadNecessaryClasses();

        //start working
	    PackManager.v().runPacks();

	    PackManager.v().writeOutput();
	}

	@Override
	protected void internalTransform(Body b, String phaseName,
		Map<String, String> options) {

		//we don't instrument Log class
		if(!b.getMethod().getDeclaringClass().getName().equals("Log"))
		{
			Iterator<Unit> it = b.getUnits().snapshotIterator();
		    while(it.hasNext()){
		    	Stmt stmt = (Stmt)it.next();
		    	if (stmt.containsFieldRef()) {
		    		//field
		    		
		    		String threadName = stmt.getFieldRef().getField().toString(); 
		    		Object obj = Thread.currentThread(); 
		    		boolean stat = stmt.getFieldRef().getFieldRef().isStatic(); 
		    		boolean write = false; 
		    		
		    		
		    		//System.out.print("Field Reference: " + stmt.getFieldRef() );
		    		
		    		//System.out.println(stmt);
		    		//logFieldAcc();
		    		
		    		
		    		//logStart(stmt);
		    		Log.logFieldAcc(obj, threadName, stat, write);

		    		//System.out.print(" Declaring class: " + stmt.getFieldRef().getField().getDeclaringClass());

		    		//System.out.println(" is static: " + stmt.getFieldRef().getFieldRef().isStatic());
		    		
		    		//your code starts here
		    	}
		    }
		}
	}
	
}
