public class SumdokuGrid {
    private int[][] grid;
    private int size;

    /**
     * Construtor da classe SumdokuGrid.
     * Inicializa uma grelha vazia com o tamanho especificado.
     * 
     * @param gridSize o tamanho da grelha (número de linhas e colunas).
     */
    public SumdokuGrid(int gridSize){
        this.grid = new int[gridSize][gridSize];
        this.size = gridSize;
    }
    
    /**
     * Obtém o valor armazenado numa célula específica da grelha.
     * 
     * @param row a linha da célula (1-indexada).
     * @param column a coluna da célula (1-indexada).
     * @return o valor armazenado na célula especificada.
     */
    public int value(int row,int column){
        return this.grid[row-1][column-1];
    }

    /**
     * Preenche uma célula específica da grelha com o valor fornecido.
     * 
     * @param row a linha da célula (1-indexada).
     * @param column a coluna da célula (1-indexada).
     * @param value o valor a ser inserido na célula.
     */
    public void fill(int row,int column,int value){
        this.grid[row-1][column-1] = value;
    }

    /**
     * Converte a grelha numa representação textual.
     * As células preenchidas exibem os seus valores, enquanto as células vazias exibem ".".
     * 
     * @return uma representação textual da grelha.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        int gridSizeRow = this.grid.length;
        int gridSizeColumn = this.grid[0].length;
        
        output.append(gridEnds());
        for (int i = 1; i <= gridSizeRow; i++) {
            for (int j = 1; j <= gridSizeColumn; j++) {
                if (this.isFilled(i, j)){
                    output.append(this.value(i, j));
                } else {
                    output.append(".");
                }
                output.append(" ");
            }
            output.append("\n");
        }    
        
        return output.toString();
    }
    
    /**
     * Gera a linha divisória usada na representação textual da grelha.
     * 
     * @return uma string que representa a linha divisória da grelha.
     */
    private String gridEnds(){
        StringBuilder divider = new StringBuilder();
        
        for (int i = 0; i < this.grid.length; i++) {
            divider.append("--");
        }

        divider.append("\n");
        return divider.toString();
    }

    /**
     * Verifica se uma célula específica da grelha está preenchida.
     * 
     * @param row a linha da célula (1-indexada).
     * @param column a coluna da célula (1-indexada).
     * @return true se a célula estiver preenchida (valor diferente de 0), false caso contrário.
     */
    public boolean isFilled(int row, int column){
        
        return grid[row - 1][column - 1] != 0;
    }
}
