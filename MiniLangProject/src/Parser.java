import java.util.*;

public class Parser {
    private Tokenizer tokenizer;

    public Parser(String input) {
        this.tokenizer = new Tokenizer(input);
    }

    public ProgramNode parseProgram() {
        ProgramNode program = new ProgramNode();
        while (tokenizer.hasNext()) {
            program.add(parseStatement());
        }
        return program;
    }

    private ASTNode parseStatement() {
        String token = tokenizer.peek();
        if (token == null) return null;

        switch (token) {
            case "let":
                tokenizer.next();
                return parseVarDecl();
            case "if":
                return parseIf();
            case "loop":
                return parseLoop();
            case "print":
                return parsePrint();
            default:
                return parseAssignment();
        }
    }

    private VarDeclNode parseVarDecl() {
        String name = tokenizer.next(); // variable name
        tokenizer.expect("=");
        ASTNode expr = parseExpr();
        return new VarDeclNode(name, expr);
    }

    private AssignNode parseAssignment() {
        String name = tokenizer.next(); // variable name
        tokenizer.expect("=");
        ASTNode expr = parseExpr();
        return new AssignNode(name, expr);
    }

    private IfNode parseIf() {
        tokenizer.expect("if");
        ASTNode condition = parseExpr();
        tokenizer.expect("then");
        List<ASTNode> thenBranch = new ArrayList<>();
        while (!"else".equals(tokenizer.peek())) {
            thenBranch.add(parseStatement());
        }
        tokenizer.expect("else");
        List<ASTNode> elseBranch = new ArrayList<>();
        while (!"end".equals(tokenizer.peek())) {
            elseBranch.add(parseStatement());
        }
        tokenizer.expect("end");
        return new IfNode(condition, thenBranch, elseBranch);
    }

    private LoopNode parseLoop() {
        tokenizer.expect("loop");
        String var = tokenizer.next();
        tokenizer.expect("from");
        ASTNode start = parseExpr();
        tokenizer.expect("to");
        ASTNode end = parseExpr();
        tokenizer.expect("do");
        List<ASTNode> body = new ArrayList<>();
        while (!"end".equals(tokenizer.peek())) {
            body.add(parseStatement());
        }
        tokenizer.expect("end");
        return new LoopNode(var, start, end, body);
    }

    private PrintNode parsePrint() {
        tokenizer.expect("print");
        ASTNode expr = parseExpr();
        return new PrintNode(expr);
    }

    private ASTNode parseExpr() {
        String left = tokenizer.next();

        ASTNode leftNode = null;
        if (left.startsWith("\"") && left.endsWith("\"")) {
            leftNode = new StringNode(left.substring(1, left.length() - 1));
        } else {
            leftNode = new VarNode(left);
        }

        if ("==".equals(tokenizer.peek())) {
            tokenizer.next(); // consume ==
            ASTNode rightNode = parseExpr();
            return new EqualsNode(leftNode, rightNode);
        }

        return leftNode;
    }
}
