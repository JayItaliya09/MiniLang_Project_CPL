import java.util.*;

abstract class ASTNode {
    abstract String toPython();
}

class ProgramNode extends ASTNode {
    List<ASTNode> statements = new ArrayList<>();

    public void add(ASTNode stmt) {
        statements.add(stmt);
    }

    @Override
    String toPython() {
        StringBuilder sb = new StringBuilder();
        for (ASTNode stmt : statements) {
            sb.append(stmt.toPython()).append("\n");
        }
        return sb.toString();
    }
}

class VarDeclNode extends ASTNode {
    String name;
    ASTNode expr;

    VarDeclNode(String name, ASTNode expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    String toPython() {
        return name + " = " + expr.toPython();
    }
}

class AssignNode extends ASTNode {
    String name;
    ASTNode expr;

    AssignNode(String name, ASTNode expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    String toPython() {
        return name + " = " + expr.toPython();
    }
}

class IfNode extends ASTNode {
    ASTNode condition;
    List<ASTNode> thenBranch, elseBranch;

    IfNode(ASTNode condition, List<ASTNode> thenBranch, List<ASTNode> elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    String toPython() {
        StringBuilder sb = new StringBuilder();
        sb.append("if ").append(condition.toPython()).append(":\n");
        for (ASTNode stmt : thenBranch)
            sb.append("    ").append(stmt.toPython()).append("\n");
        sb.append("else:\n");
        for (ASTNode stmt : elseBranch)
            sb.append("    ").append(stmt.toPython()).append("\n");
        return sb.toString();
    }
}

class LoopNode extends ASTNode {
    String var;
    ASTNode start, end;
    List<ASTNode> body;

    LoopNode(String var, ASTNode start, ASTNode end, List<ASTNode> body) {
        this.var = var;
        this.start = start;
        this.end = end;
        this.body = body;
    }

    @Override
    String toPython() {
        StringBuilder sb = new StringBuilder();
        sb.append("for ").append(var).append(" in range(")
                .append(start.toPython()).append(", ")
                .append(end.toPython()).append(" + 1):\n");
        for (ASTNode stmt : body)
            sb.append("    ").append(stmt.toPython()).append("\n");
        return sb.toString();
    }
}

class PrintNode extends ASTNode {
    ASTNode expr;

    PrintNode(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    String toPython() {
        return "print(" + expr.toPython() + ")";
    }
}

class StringNode extends ASTNode {
    String value;

    StringNode(String value) {
        this.value = value;
    }

    @Override
    String toPython() {
        return "\"" + value + "\"";
    }
}

class VarNode extends ASTNode {
    String name;

    VarNode(String name) {
        this.name = name;
    }

    @Override
    String toPython() {
        return name;
    }
}

class EqualsNode extends ASTNode {
    ASTNode left, right;

    EqualsNode(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    String toPython() {
        return left.toPython() + " == " + right.toPython();
    }
}
